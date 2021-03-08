package org.elib;

import org.elib.repository.entity.Book;
import org.elib.repository.entity.User;
import org.elib.services.ELibService;
import org.elib.services.model.BookDto;
import org.elib.services.model.UserDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/elibrary")
public class ELibraryResource {

    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }

    @Inject
    ELibService eLibService;

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers()
    {
        List<UserDto> userList = eLibService.findAllUsers();
        if(userList == null || userList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(userList).build();
    }

    @POST
    @Path("/user/register")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registerUser(UserDto user, @Context UriInfo uriInfo)
    {
        user = eLibService.registerUser(user);
        if(user != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString((Integer) user.getId()));
            return Response.created(uriBuilder.build()).entity(user).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("IN-NM", "User already exists.")) .build();
    }

    @POST
    @Path("/user/login")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response loginUser(UserDto user, @Context UriInfo uriInfo)
    {
        user = eLibService.loginUser(user);
        if(user != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString((Integer) user.getId()));
            return Response.created(uriBuilder.build()).entity(user).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("IN-NM", "User already exists.")) .build();
    }

    @GET
    @Path("/book")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks()
    {
        List<BookDto> bookDtoList = eLibService.findAllBooks();
        if(bookDtoList == null || bookDtoList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(bookDtoList).build();
    }

    @GET
    @Path("/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") Integer id)
    {
        BookDto book = eLibService.getBook(id);
        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Path("/book/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBook(BookDto book, @Context UriInfo uriInfo)
    {
        book = eLibService.addBook(book);
        if(book != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString((Integer) book.getId()));
            return Response.ok(uriBuilder.build()).entity(book).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("WI-00", "Wrong input.")) .build();
    }

    @PUT
    @Path("/book/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editBook(BookDto book, @Context UriInfo uriInfo)
    {
        book = eLibService.editBook(book);
        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @DELETE
    @Path("/book/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Integer id)
    {
        return Response.ok(eLibService.deleteBook(id)).build();
    }

    @PUT
    @Path("/book/{id}/take")
    @Produces(MediaType.APPLICATION_JSON)
    public Response takeBook(@PathParam("id") Integer id, UserDto user)
    {
        BookDto book = eLibService.takeBook(user, id);
        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @PUT
    @Path("/book/{id}/open")
    @Produces(MediaType.APPLICATION_JSON)
    public Response openBook(@PathParam("id") Integer id, UserDto user)
    {
        BookDto book = eLibService.openBook(user, id);
        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }

    @PUT
    @Path("/book/{id}/return")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBook(@PathParam("id") Integer id)
    {
        BookDto book = eLibService.returnBook(id);
        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(book).build();
    }
}