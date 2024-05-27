package com.aluracursos.foro_hub.domain.author;

import com.aluracursos.foro_hub.domain.comment.Comment;
import com.aluracursos.foro_hub.domain.profile.Profile;
import com.aluracursos.foro_hub.domain.profile.ProfileCreationData;
import com.aluracursos.foro_hub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "authors")
@Entity(name = "author")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Author implements UserDetails {
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
        name = data.name();
        email = data.email();
        var encoder = new BCryptPasswordEncoder();
        password =  encoder.encode( data.password());
        profiles.add( new Profile(this, new ProfileCreationData( name ) ) );
    }

    public void addTopics(Topic topic){ topics.add(topic); }

    public void addProfile(Profile profile){ profiles.add(profile); }

    public void addComment(Comment comment){ comments.add(comment); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }
}
