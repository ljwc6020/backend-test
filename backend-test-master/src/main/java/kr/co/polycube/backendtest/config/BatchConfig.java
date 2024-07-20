package kr.co.polycube.backendtest.config;

import kr.co.polycube.backendtest.domain.Lotto;
import kr.co.polycube.backendtest.domain.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class BatchConfig {

    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final JobLauncher jobLauncher;
    private final WinnerRepository winnerRepository;
    private final LottoRepository lottoRepository;

    @Bean
    public Job lottoJob(JobRepository jobRepository) {
        JobBuilder jobBuilder = new JobBuilder("lottoJob", jobRepository);
        Step step = lottoStep(jobRepository);
        return jobBuilder
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step lottoStep(JobRepository jobRepository) {
        StepBuilder stepBuilder = new StepBuilder("lottoStep", jobRepository);
        return stepBuilder
                .tasklet(lottoTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet lottoTasklet() {
        return (contribution, chunkContext) -> {
            checkLottoWinners();
            return RepeatStatus.FINISHED;
        };
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void performLottoJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(lottoJob(null), jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkLottoWinners() {
        int[] winningNumbers = {1, 2, 3, 4, 5, 6};
        List<Lotto> lottos = lottoRepository.findAll();
        for (Lotto lotto : lottos) {
            int matchedNumbers = getMatchedNumbers(lotto, winningNumbers);
            if (matchedNumbers >= 3) {
                Winner winner = new Winner();
                winner.setId(lotto.getUser().getId());

                winner.setRank(getRank(matchedNumbers));
                winnerRepository.save(winner);
            }
        }
    }

    private int getMatchedNumbers(Lotto lotto, int[] winningNumbers) {
        int[] lottoNumbers = {
                lotto.getNumber1(),
                lotto.getNumber2(),
                lotto.getNumber3(),
                lotto.getNumber4(),
                lotto.getNumber5(),
                lotto.getNumber6()
        };
        int matchedCount = 0;
        for (int lottoNumber : lottoNumbers) {
            for (int winningNumber : winningNumbers) {
                if (lottoNumber == winningNumber) {
                    matchedCount++;
                }
            }
        }
        return matchedCount;
    }

    private int getRank(int matchedNumbers) {
        switch (matchedNumbers) {
            case 6: return 1;
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            default: return 5;
        }
    }
}
