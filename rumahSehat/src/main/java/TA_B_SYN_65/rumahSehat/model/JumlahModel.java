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
    @NotNull
    @Size(max = 50)
    @Column(name = "obat", nullable = false)
    private String obat;

    @NotNull
    @Column(name = "resep", nullable = false)
    private Long resep;

    @Size(max = 200)
    @Column(name = "kuantitas")
    Integer kuantitas;
}



