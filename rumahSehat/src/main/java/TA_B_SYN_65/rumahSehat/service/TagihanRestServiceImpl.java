package TA_B_SYN_65.rumahSehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {

   @Autowired
   TagihanDb tagihanDb;

   @Override
   public List<TagihanModel> retrieveListTagihan() {
      return tagihanDb.findAll();
   }

   @Override
   public TagihanModel getTagihanByCode(String code) {
      return tagihanDb.findByCode(code);
   }
}
