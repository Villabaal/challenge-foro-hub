package com.aluracursos.foro_hub.domain.author;

import com.aluracursos.foro_hub.domain.comment.Comment;
import com.aluracursos.foro_hub.domain.profile.Profile;
import com.aluracursos.foro_hub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.token.Sha512DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Table(name = "authors")
@Entity(name = "author")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Profile> profiles = new ArrayList<>();

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();


    public Author( AuthorRegisterData data ) {
        this.name = data.name();
        this.email = data.email();
        this.password = Sha512DigestUtils.shaHex( data.password() ) ;
    }

    public void addTopics(Topic topic){ topics.add(topic); }

    public void addProfile(Profile profile){ profiles.add(profile); }

    public void addComment(Comment comment){ comments.add(comment); }

}
