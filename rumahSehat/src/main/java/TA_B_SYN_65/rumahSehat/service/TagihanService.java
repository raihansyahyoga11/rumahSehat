package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.TagihanModel;

import java.util.List;

public interface TagihanService {

    List<TagihanModel> getListTagihan();

    void createTagihan(AppointmentModel appointment);

    void save(TagihanModel tagihan);
}
