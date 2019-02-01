package reportapplication.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reportapplication.demo.Model.FileModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {

    @Query(
            value = "SELECT * FROM file_model WHERE id = ?1",
            nativeQuery = true)
    List<FileModel> filesById(@Param("id") Long id);

}
