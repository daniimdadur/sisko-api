package practice.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.fakultas.model.FakultasRes;
import practice.api.fakultas.repository.FakultasRepo;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final FakultasRepo fakultasRepo;

    @Override
    public void run(String... args) throws Exception {
        initFakultas();
    }

    private void initFakultas() {
        if (!fakultasRepo.findAll().isEmpty()) {
            return;
        }

        List<FakultasEntity> fakultasList = new ArrayList<>();
        FakultasEntity fk = new FakultasEntity("f47ac10b-58cc-4372-a567-0e02b2c3d479", "F001", "Fakultas Kedokteran");

        //generate data
        fakultasList.add(fk);
        try {
            this.fakultasRepo.saveAll(fakultasList);
            log.info("fakultas list saved");
        } catch (Exception e) {
            log.error("fakultas list save failed, error: {}", e.getMessage());
        }
    }
}
