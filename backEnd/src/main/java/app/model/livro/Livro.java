package app.model.livro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liv_id")
    private Integer id;

    @Column(name = "liv_autor")
    private String autor;

    @ManyToMany
    @JoinTable(
        name = "livros_categorias",
        joinColumns = @JoinColumn(name = "lvc_liv_id"),
        inverseJoinColumns = @JoinColumn(name = "lvc_cat_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @Column(name = "liv_ano")
    private Integer ano;

    @Column(name = "liv_titulo")
    private String titulo;

    @Column(name = "liv_editora")
    private String editora;

    @Column(name = "liv_edicao")
    private String edicao;

    //PRECISA ALTERAR NO BANCO DO SERVIÇO
    @Column(name = "liv_isbn", length = 20)
    private String isbn;

    @Column(name = "liv_qt_pag")
    private Integer qtPag;

    @Column(name = "liv_sinopse")
    private String sinopse;

    @Column(name = "liv_cod_barras", length = 13) // Geralmente EAN-13 usa 13 dígitos
    private String codBarras;

    @OneToOne
    @JoinColumn(name = "liv_pre_id")
    private Precificacao precificacao;

    @OneToOne
    @JoinColumn(name = "liv_dim_id")
    private Dimensao dimensao;

    // @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Precificacao precificacao;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens;
    
    @OneToOne(mappedBy = "livro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Estoque estoque;

    @Column(name = "liv_status") // Geralmente EAN-13 usa 13 dígitos
    private Boolean status;
}
