package TA_B_SYN_65.rumahSehat.service;

import java.util.List;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;

public interface TagihanRestService {
   List<TagihanModel> retrieveListTagihan();

   TagihanModel getTagihanByCode(String code);

   boolean pay(String code, String username);
}
