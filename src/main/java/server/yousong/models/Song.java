package server.yousong.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Setter
@Getter
@Embeddable
@CrossOrigin
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    //private String genre;
    @ElementCollection
    public Collection<String> genre;
    private String length;
    @Version
    private long version;

   public long getETag() {
       return version;
   }

   @Projection(name = "kompakt", types = Song.class)
    public interface SongProjection{
       String getTitle();
   }
}

