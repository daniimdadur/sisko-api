package practice.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import practice.api.course.model.CourseEntity;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.fakultas.model.FakultasRes;
import practice.api.fakultas.repository.FakultasRepo;
import practice.api.majors.model.MajorsEntity;
import practice.api.student.model.StudentEntity;

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
        MajorsEntity dh = new MajorsEntity("1bfcad83-c381-4eca-98c5-ab209857e894", "DH", "Dokter Hewan");
        StudentEntity sabil = new StudentEntity("9f70958d-3bdc-4ac8-8782-2f475f540c8e", "200305", "Sabilla");
        CourseEntity vaksinasi = new CourseEntity("fc47f4cb-b3c2-4629-ae70-3f3af55c847a", "H001", "Vaksinasi", "Dr. Kureha", "9.0");

        fk.addMajor(dh);
        dh.addStudent(sabil);
        sabil.addCourse(vaksinasi);

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
