package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;

import java.util.List;

public interface TagihanService {

    void createTagihan(TagihanModel tagihan);
    List<TagihanModel> getListTagihan();
}
