package cz.eg.hr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class JavascriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    private String version;

    private Integer rating;

    private LocalDate endOfSupport;

    public JavascriptFramework() {}

    public JavascriptFramework(String name, String version, Integer rating, LocalDate endOfSupport) {
        this.name = name;
        this.version = version;
        this.rating = rating;
        this.endOfSupport = endOfSupport;
    }

    @Override
    public String toString() {
        return "JavascriptFramework{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
