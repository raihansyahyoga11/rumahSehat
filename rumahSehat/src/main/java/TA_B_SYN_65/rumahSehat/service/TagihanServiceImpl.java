package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;
import TA_B_SYN_65.rumahSehat.security.xml.Attributes;
import TA_B_SYN_65.rumahSehat.security.xml.ServiceResponse;
import TA_B_SYN_65.rumahSehat.service.TagihanService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import TA_B_SYN_65.rumahSehat.setting.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public void createTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    @Override
    public List<TagihanModel> getListTagihan() { return tagihanDb.findAll(); }
}

