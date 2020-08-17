package com.test.springDataJPA.Service;

import com.test.springDataJPA.Model.User;
import com.test.springDataJPA.Repository.UserRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listUser() {
        return userRepository.findAll();
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public User get(long id) {
        return userRepository.findById(id).get();
    }
    public void delete(long id) {
        userRepository.deleteById(id);
    }
//    public User findUser(String user){
//        return userRepository.findByUserLike(user);
//    }
    public Page<User> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;

        if (listUser().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listUser().size());
            list = listUser().subList(startItem, toIndex);
        }

        Page<User> userPage
                = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), listUser().size());

        return userPage;
    }
//    private List<User> users(){
//        JasperReport jasperReport = JasperCompileManager.compileReport()
//        return users();
//    }


}
