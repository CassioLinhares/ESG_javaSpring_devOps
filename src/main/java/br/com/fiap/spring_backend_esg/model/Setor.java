package br.com.fiap.spring_backend_esg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_setor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Setor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_tbl_setor"
    )
    @SequenceGenerator(
            name = "seq_tbl_setor",
            sequenceName = "seq_tbl_setor",
            allocationSize = 1
    )
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
