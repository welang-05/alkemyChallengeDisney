package Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String imagen, titulo;

    private Date fechaCreacion;

    private int calificacion;

    @ManyToMany(mappedBy = "filmes", fetch = FetchType.LAZY)
    private Set<Personaje> personajes = new HashSet<>();


}
