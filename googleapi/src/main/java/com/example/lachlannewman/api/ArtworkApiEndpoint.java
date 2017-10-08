package com.example.lachlannewman.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "artworkApiApi",
        version = "v1",
        resource = "artworkApi",
        namespace = @ApiNamespace(
                ownerDomain = "api.lachlannewman.example.com",
                ownerName = "api.lachlannewman.example.com",
                packagePath = ""
        )
)
public class ArtworkApiEndpoint {

    private static final Logger logger = Logger.getLogger(ArtworkApiEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(ArtworkApi.class);
    }

    /**
     * Returns the {@link ArtworkApi} with the corresponding ID.
     *
     * @param Id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code ArtworkApi} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "{exhibit_id}/artworkApi/{Id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public ArtworkApi get(@Named("exhibit_id") Long exhibit_id,@Named("Id") Long Id) throws NotFoundException {
        logger.info("Getting ArtworkApi with ID: " + Id);
        ArtworkApi artworkApi = (ArtworkApi) ofy().load().type(ArtworkApi.class).filter("exhibit_id",exhibit_id);
        if (artworkApi == null) {
            throw new NotFoundException("Could not find ArtworkApi with ID: " + Id);
        }
        return artworkApi;
    }

    /**
     * Inserts a new {@code ArtworkApi}.
     */
    @ApiMethod(
            name = "insert",
            path = "artworkApi",
            httpMethod = ApiMethod.HttpMethod.POST)
    public ArtworkApi insert(ArtworkApi artworkApi) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that artworkApi.Id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(artworkApi).now();
        logger.info("Created ArtworkApi.");

        return ofy().load().entity(artworkApi).now();
    }

    /**
     * Updates an existing {@code ArtworkApi}.
     *
     * @param Id         the ID of the entity to be updated
     * @param artworkApi the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code ArtworkApi}
     */
    @ApiMethod(
            name = "update",
            path = "artworkApi/{Id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public ArtworkApi update(@Named("Id") Long Id, ArtworkApi artworkApi) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(Id);
        ofy().save().entity(artworkApi).now();
        logger.info("Updated ArtworkApi: " + artworkApi);
        return ofy().load().entity(artworkApi).now();
    }

    /**
     * Deletes the specified {@code ArtworkApi}.
     *
     * @param Id the ID of the entity to delete
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code ArtworkApi}
     */
    @ApiMethod(
            name = "remove",
            path = "artworkApi/{Id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("Id") Long Id) throws NotFoundException {
        checkExists(Id);
        ofy().delete().type(ArtworkApi.class).id(Id).now();
        logger.info("Deleted ArtworkApi with ID: " + Id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "artworkApi",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<ArtworkApi> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<ArtworkApi> query = ofy().load().type(ArtworkApi.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<ArtworkApi> queryIterator = query.iterator();
        List<ArtworkApi> artworkApiList = new ArrayList<ArtworkApi>(limit);
        while (queryIterator.hasNext()) {
            artworkApiList.add(queryIterator.next());
        }
        return CollectionResponse.<ArtworkApi>builder().setItems(artworkApiList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long Id) throws NotFoundException {
        try {
            ofy().load().type(ArtworkApi.class).id(Id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find ArtworkApi with ID: " + Id);
        }
    }
}