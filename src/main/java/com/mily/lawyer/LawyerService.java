package com.mily.lawyer;

import com.mily.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LawyerService {

    private final LawyerRepository lawyerRepository;

    public List<Lawyer> getList() {
        return this.lawyerRepository.findAll();
    }

    public void create(String subject, String content){
        Lawyer l = new Lawyer();
        l.setSubject(subject);
        l.setContent(content);
        this.lawyerRepository.save(l);
    }

    public Lawyer getLawyer(Integer id) {
        Optional<Lawyer> lawyer = this.lawyerRepository.findById(id);
        if(lawyer.isPresent()) {
            return lawyer.get();
        }else {
            throw new DataNotFoundException("lawyer not found");
        }
    }
}
