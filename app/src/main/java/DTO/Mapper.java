package DTO;

import android.graphics.Bitmap;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<Library> listLibraryDTO2listLibrary(List<LibraryDTO>  list) throws NullPointerException {
        List<Library> data = new ArrayList();
        for(LibraryDTO obj : list){
            Library i = libraryDTO2Library(obj);
            data.add(i);
        }
        return data;
    }

    public static Library libraryDTO2Library(LibraryDTO obj) {

        Library data = new Library(obj.getName(), obj.getId(), obj.getAddress() , obj.getOpen(), obj.getOpenDays(), obj.getOpenStatement(), obj.getOpenTime(), obj.getCloseTime());
        return data;

    }

    public static CreateLibraryBookRequest createLibraryBookRequestDTO2CreateLibraryBookRequest(CreateLibraryBookRequestDTO obj) {

        CreateLibraryBookRequest data = new CreateLibraryBookRequest(obj.getStock());
        return data;

    }

    public static List<LibraryBook> listLibraryBookDTO2listLibraryBook(List<LibraryBookDTO>  list) throws NullPointerException {
        List<LibraryBook> data = new ArrayList();
        for(LibraryBookDTO obj : list){
            LibraryBook i = libraryBookDTO2LibraryBook(obj);
            data.add(i);
        }
        return data;
    }

    private static LibraryBook libraryBookDTO2LibraryBook(LibraryBookDTO obj) {
        LibraryBook data = new LibraryBook(obj.getAvailable(), obj.getBook(), obj.getCheckedOut(),obj.getIsbn(), obj.getLibrary(), obj.getStock());
        return data;
    }


}


