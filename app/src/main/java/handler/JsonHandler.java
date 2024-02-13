package handler;
import java.util.ArrayList;

import DTO.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import model.*;
import model.CoverUrls;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    public static LibraryDTO deSerializeJson2LibraryDTO(String resp) throws JSONException {
        LibraryDTO data = new LibraryDTO();
        JSONObject mResponseObject = new JSONObject(resp);
        data.setName(mResponseObject.getString("name"));
        data.setId(mResponseObject.getString("id"));
        data.setAddress(mResponseObject.getString("address"));
        data.setOpen(mResponseObject.getBoolean("open"));
        data.setOpenDays(mResponseObject.getString("openDays"));
        data.setOpenStatement(mResponseObject.getString("openStatement"));
        data.setOpenTime(mResponseObject.getString("openTime"));
        data.setCloseTime(mResponseObject.getString("closeTime"));

        return data;
    }

    public static String serializeLibraryDTO2Json(LibraryDTO obj) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", obj.getName());
        jsonObject.put("address", obj.getAddress());
        jsonObject.put("closeTime", obj.getCloseTime());
        jsonObject.put("open", obj.getOpen());
        jsonObject.put("openDays", obj.getOpenDays());
        jsonObject.put("openStatement", obj.getOpenStatement());
        jsonObject.put("openTime", obj.getOpenTime());

        return jsonObject.toString();
    }

    public static List<LibraryDTO> deSerializeJson2ListLibraryDTO(String resp) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        List<LibraryDTO> list = new ArrayList<>();
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            String name = jsonChildNode.optString("name");
            String id = jsonChildNode.optString("id");
            String address = jsonChildNode.optString("address");
            boolean open = jsonChildNode.optBoolean("open");
            String openDays = jsonChildNode.optString("openDays");
            String openStatement = jsonChildNode.optString("openStatement");
            String openTime = jsonChildNode.optString("openTime");
            String closeTime = jsonChildNode.optString("closeTime");

            list.add(new LibraryDTO(name , id, address, open, openDays, openStatement, openTime, closeTime));
        }
        return list;
    }


    public static List<LibraryBookDTO> deSerializeJson2ListLibraryBookDTO(String resp) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        List<LibraryBookDTO> list = new ArrayList<>();
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            int available = jsonChildNode.getInt("available");

            JSONObject bookJsonObject = jsonChildNode.getJSONObject("book");
            Book book = deserializeJson2Book(bookJsonObject);

            int checkedOut = jsonChildNode.getInt("checkedOut");
            String isbn = jsonChildNode.optString("isbn");
            //Library library = (Library) jsonChildNode.get("library");
            JSONObject libraryJsonObject = jsonChildNode.getJSONObject("library");
            Library library = deserializeJson2Library(libraryJsonObject);


            int stock = jsonChildNode.getInt("stock");

            list.add(new LibraryBookDTO(available, book, checkedOut, isbn, library, stock));
        }
        return list;
    }

    private static Book deserializeJson2Book(JSONObject bookJsonObject) throws JSONException {
        Book book = new Book();
        book.setAuthors(deserializeJson2Author(bookJsonObject.getJSONArray("authors")));
        book.setByStatement(bookJsonObject.optString("byStatement"));
        book.setCover(deserializeJson2Cover(bookJsonObject.getJSONObject("cover")));
        book.setDescription(bookJsonObject.optString("description"));
        book.setIsbn(bookJsonObject.optString("isbn"));
        book.setNumberOfPages(bookJsonObject.optString("numberOfPages"));
        book.setPublishDate(bookJsonObject.optString("publishDate"));
        book.setTitle(bookJsonObject.optString("title"));
        book.setSubjectPeople(bookJsonObject.optString("subjectPeople"));
        book.setSubjectPlaces(bookJsonObject.optString("subjectPlaces"));
        book.setSubjectTimes(bookJsonObject.optString("subjectTimes"));
        book.setSubjects(bookJsonObject.optString("subjects"));

        return book;
    }

    private static CoverUrls deserializeJson2Cover(JSONObject cover) {
        CoverUrls coverUrls = new CoverUrls();
        coverUrls.setSmallUrl(cover.optString("small"));
        coverUrls.setMediumUrl(cover.optString("medium"));
        coverUrls.setLargeUrl(cover.optString("large"));
        return coverUrls;
    }

    private static List<Author> deserializeJson2Author(JSONArray jsonAuthors) throws JSONException {
        List<Author> authors = new ArrayList<>();

        for (int j = 0; j < jsonAuthors.length(); j++) {
            JSONObject jsonAuthor = jsonAuthors.getJSONObject(j);
            String authorName = jsonAuthor.getString("name");
            String authorBio = jsonAuthor.optString("bio", null);
            String authorBirthDate = jsonAuthor.optString("birthDate", null);
            String authorDeathDate = jsonAuthor.optString("deathDate", null);
            String authorId = jsonAuthor.optString("id", null);

            JSONArray jsonAlternateNames = jsonAuthor.optJSONArray("alternateNames");
            List<String> alternateNames = new ArrayList<>();
            if (jsonAlternateNames != null) {
                for (int k = 0; k < jsonAlternateNames.length(); k++) {
                    String alternateName = jsonAlternateNames.getString(k);
                    alternateNames.add(alternateName);
                }
            }
            Author author = new Author(authorId, authorName, authorBio, authorBirthDate, authorDeathDate, alternateNames);
            authors.add(author);
        }
        return authors;
    }

    private static Library deserializeJson2Library(JSONObject libraryJsonObject) throws JSONException {
        Library library = new Library();
        library.setName(libraryJsonObject.getString("name"));
        library.setId(libraryJsonObject.getString("id"));
        library.setAddress(libraryJsonObject.getString("address"));
        library.setOpen(libraryJsonObject.getBoolean("open"));
        library.setOpenDays(libraryJsonObject.getString("openDays"));
        library.setOpenStatement(libraryJsonObject.getString("openStatement"));
        library.setOpenTime(libraryJsonObject.getString("openTime"));
        library.setCloseTime(libraryJsonObject.getString("closeTime"));

        return library;
    }

    public static String serializeCreateLibraryBookRequestDTO2Json(CreateLibraryBookRequestDTO obj) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stock", obj.getStock());

        return jsonObject.toString();
    }

    public static CreateLibraryBookRequestDTO deSerializeJson2CreateLibraryBookRequestDTO(String resp) throws JSONException {
        CreateLibraryBookRequestDTO data = new CreateLibraryBookRequestDTO();
        JSONObject mResponseObject = new JSONObject(resp);
        data.setStock(Integer.parseInt(mResponseObject.getString("stock")));

        return data;
    }


}