package com.haiqiu.rest;

import com.haiqiu.dao.CategoryDAO;
import com.haiqiu.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by T430S on 2016/1/19.
 */
@Path("/categoryservice")
@Produces({"application/json","application/xml"})
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO ;


    @GET
    @Path("/category/{id}")
    @Produces({"application/json","application/xml"})
    public Category getCategory(@PathParam("id") String id) {
        //http://localhost:8081/myframe/services/rest/categoryservice/category/001
        System.out.println("getCategory called with category id: " + id);

        Category cat = (Category) getCategoryDAO().getCategory(id);
        if (cat == null) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Category Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return cat;
        }
    }


    @POST
    @Path("/category")
    @Consumes({"application/json","application/xml"})
    public Response addCategory(Category category) {

        System.out.println("addCategory called");

        Category cat = (Category) getCategoryDAO().getCategory(
                category.getCategoryId());

        if (cat != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().addCategory(category);
            return Response.ok(category).build();
        }

    }



    @DELETE
    @Path("/category/{id}")
    @Consumes({"application/json","application/xml"})
    public Response deleteCategory(@PathParam("id") String id) {

        System.out.println("deleteCategory with category id : " + id);

        Category cat = (Category) getCategoryDAO().getCategory(id);
        if (cat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().deleteCategory(id);
            return Response.ok().build();
        }
    }

    @PUT
    @Path("/category")
    @Consumes({"application/json","application/xml"})
    public Response updateCategory(Category category) {

        System.out.println("updateCategory with category id : "
                + category.getCategoryId());

        Category cat = (Category) getCategoryDAO().getCategory(
                category.getCategoryId());
        if (cat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().updateCategory(category);
            return Response.ok(category).build();
        }
    }


    @POST
    @Path("/category/book")
    @Consumes({"application/json","application/xml"})
    public Response addBooks(Category category) {

        System.out.println("addBooks with category id : "
                + category.getCategoryId());

        Category cat = (Category) getCategoryDAO().getCategory(
                category.getCategoryId());
        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            getCategoryDAO().addBook(category);
            return Response.ok(category).build();
        }
    }


    @GET
    @Path("/category/{id}/books")
    @Consumes("application/xml,application/json")
    public Response getBooks(@PathParam("id") String id) {

        System.out.println("getBooks called with category id : " + id);

        Category cat = (Category) getCategoryDAO().getCategory(id);

        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            cat.setBooks(getCategoryDAO().getBooks(id));
            return Response.ok(cat).build();

        }
    }


    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}




