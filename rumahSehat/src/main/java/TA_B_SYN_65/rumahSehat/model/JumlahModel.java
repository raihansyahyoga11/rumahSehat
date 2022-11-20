package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel{

    @Id
    @Size(max = 50)
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "id_obat", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatModel obat;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "id_resep", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResepModel resep;

    @NotNull
    @Size(max = 200)
    @Column(name = "kuantitas")
    private Integer kuantitas;
}



