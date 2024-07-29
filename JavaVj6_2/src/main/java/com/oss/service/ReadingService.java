package com.oss.service;


import com.oss.model.Reading;
import java.util.*;
import com.oss.form.ReadingForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadingService {
    void enterReading(ReadingForm readingForm);
    List<Reading> getAllReadings();
    Page<Reading> getAllReadings(Pageable pageable);
}
