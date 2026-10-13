package br.com.fiap.spring_backend_esg.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Empresa {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_tbl_empresa"
    )
    @SequenceGenerator(
            name = "seq_tbl_empresa",
            sequenceName = "seq_tbl_empresa",
            allocationSize = 1
    )
    private Long id;

    private String name;

    @Column(unique = true)
    private String cnpj;
}
