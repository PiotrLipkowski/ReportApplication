package reportapplication.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reportapplication.demo.Model.FileModel;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
}
