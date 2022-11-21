package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin")
@Inheritance(strategy = InheritanceType.JOINED)
public class AdminModel extends UserModel implements Serializable {
}
