package org.git.yhugorocha.resources;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.git.yhugorocha.dto.Product;
import org.git.yhugorocha.service.ProductService;

@AllArgsConstructor
@Path("/products")
public class ProductResource {

    private final ProductService productService;

    @GET
    public Response getProducts() {
        return Response.ok(productService.getAllProducts()).build();
    }
    @POST
    public Response createdProduct(@RequestBody @Valid Product product, @Context UriInfo uriInfo) {
        var response = productService.created(product);
        UriBuilder uri = uriInfo.getAbsolutePathBuilder();
        uri.path(response.getId().toString());
        return Response.created(uri.build()).build();
    }

    @Path("/{id}")
    @PUT
    public Response updateProduct(@PathParam("id")Long id, @RequestBody @Valid Product product) {
        return Response.ok(productService.update(product, id)).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteProduct(@PathParam("id")Long id) {
        productService.delete(id);
        return Response.ok().build();
    }
}
