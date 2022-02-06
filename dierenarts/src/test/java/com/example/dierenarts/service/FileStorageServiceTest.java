package com.example.dierenarts.service;

import com.example.dierenarts.model.FileDB;
import com.example.dierenarts.repository.FileDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class FileStorageServiceTest {

    @Mock
    FileDBRepository repository;

    @InjectMocks
    FileStorageService service;

    @Captor
    ArgumentCaptor<FileDB> captor;

    @Test
    void storeTest() {
        FileDB file = new FileDB();
        file.setName("file");

        repository.save(file);

        verify(repository, times(1)).save(captor.capture());
        var file1 = captor.getValue();

        assertThat(file1.getName()).isEqualTo("file");
    }

    @Test
    void getFileTest() {
        FileDB file = new FileDB();
        file.setId(1L);
        file.setName("file");

        when(repository.findById(1L)).thenReturn(Optional.of(file));

        service.getFile(1L);
    }

    @Test
    void getAllFilesTest() {
        List<FileDB> testFiles = new ArrayList<>();
        FileDB file1 = new FileDB();
        file1.setId(1L);
        file1.setName("file1");
        FileDB file2 = new FileDB();
        file2.setId(2L);
        file2.setName("file2");

        testFiles.add(file1);
        testFiles.add(file2);

        when(repository.findAll()).thenReturn(testFiles);

        service.getAllFiles();

        verify(repository, times(1)).findAll();

        assertThat(testFiles.size()).isEqualTo(2);
        assertThat(testFiles.get(0)).isEqualTo(file1);
        assertThat(testFiles.get(1)).isEqualTo(file2);
    }
}