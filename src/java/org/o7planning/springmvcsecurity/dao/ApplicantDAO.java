
package org.o7planning.springmvcsecurity.dao;

import java.util.List;
import org.o7planning.springmvcsecurity.entity.Applicant;
import org.o7planning.springmvcsecurity.model.ApplicantInfo;


public interface ApplicantDAO {
public Applicant findApplicant(String id);
public List<ApplicantInfo> listApplicantInfos();
public void saveApplicant(ApplicantInfo applicantInfo );
public ApplicantInfo findApplicantInfo(String id);
public void deleteApplicant(String id);

    
}
