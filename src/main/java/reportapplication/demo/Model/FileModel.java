package reportapplication.demo.Model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name="file_model")
public class FileModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(View.FileInfo.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.FileInfo.class)
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name="pic")
    private byte[] pic;


    @Column(name = "userName")
    @JsonView(View.FileInfo.class)
    private String userName;

    public FileModel(){}

    public FileModel(String name, String mimetype, byte[] pic, String userName){
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
        this.userName = userName;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMimetype(){
        return this.mimetype;
    }

    public void setMimetype(String mimetype){
        this.mimetype = mimetype;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}