package com.mily.appointment;

import com.mily.lawyer.Lawyer;
import com.mily.lawyer.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private  final AppointmentRepository appointmentRepository;

    private final LawyerRepository lawyerRepository;

    public void create(Lawyer lawyer, String content) {
        Appointment appointment = new Appointment();
        appointment.setContent(content);
        appointment.setLawyer(lawyer);
        this.lawyerRepository.save(lawyer);
    }

}
