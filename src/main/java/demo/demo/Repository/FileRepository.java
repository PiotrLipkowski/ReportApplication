package demo.demo.Repository;

import demo.demo.Model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
}