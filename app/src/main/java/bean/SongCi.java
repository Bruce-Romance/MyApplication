package bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SongCi {

    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String type;
    private String content;
    private String author;

    @Generated(hash = 238680902)
    public SongCi(Long id, String title, String type, String content,
            String author) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.content = content;
        this.author = author;
    }
    @Generated(hash = 1213606738)
    public SongCi() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

}
