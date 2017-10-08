package com.example.lachlannewman.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Key;
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
        name = "exhibitApiApi",
        version = "v1",
        resource = "exhibitApi",
        namespace = @ApiNamespace(
                ownerDomain = "api.lachlannewman.example.com",
                ownerName = "api.lachlannewman.example.com",
                packagePath = ""
        )
)
public class ExhibitApiEndpoint {

    private static final Logger logger = Logger.getLogger(ExhibitApiEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(ExhibitApi.class);
    }

    /**
     * Returns the {@link ExhibitApi} with the corresponding ID.
     *
     * @param exhibitWSKey the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code ExhibitApi} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "exhibitApi/{exhibit_web_safe_key}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public ExhibitApi get(@Named("exhibit_web_safe_key") String exhibitWSKey) throws NotFoundException {
        logger.info("Getting ExhibitApi with ID: " + exhibitWSKey);
        ExhibitApi exhibitApi = (ExhibitApi) ofy().load().key(Key.create(exhibitWSKey)).now();
        if (exhibitApi == null) {
            throw new NotFoundException("Could not find ExhibitApi with ID: " + exhibitWSKey);
        }
        return exhibitApi;
    }

    /**
     * Inserts a new {@code ExhibitApi}.
     */
    @ApiMethod(
            name = "insert",
            path = "exhibitApi",
            httpMethod = ApiMethod.HttpMethod.POST)
    public ExhibitApi insert(ExhibitApi exhibitApi) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that exhibitApi.Id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(exhibitApi).now();
        logger.info("Created ExhibitApi.");

        return ofy().load().entity(exhibitApi).now();
    }

    /**
     * Updates an existing {@code ExhibitApi}.
     *
     * @param exhibitWSKey the ID of the entity to be updated
     * @param exhibitApi the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code ExhibitApi}
     */
    @ApiMethod(
            name = "update",
            path = "exhibitApi/{exhibit_web_safe_key}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public ExhibitApi update(@Named("exhibit_web_safe_key") String exhibitWSKey, ExhibitApi exhibitApi) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        Key<ExhibitApi> exhibitKey = Key.create(exhibitWSKey);
        checkExists(exhibitKey);
        ofy().save().entity(exhibitApi).now();
        logger.info("Updated ExhibitApi: " + exhibitApi);
        return ofy().load().entity(exhibitApi).now();
    }

    /**
     * Deletes the specified {@code ExhibitApi}.
     *
     * @param exhibitWSKey the ID of the entity to delete
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code ExhibitApi}
     */
    @ApiMethod(
            name = "remove",
            path = "exhibitApi/{exhibit_web_safe_key}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("exhibit_web_safe_key") String exhibitWSKey) throws NotFoundException {
        Key<ExhibitApi> exhibitKey = Key.create(exhibitWSKey);
        checkExists(exhibitKey);
        //ofy().delete()..now();
        ofy().delete().keys(ofy().load().ancestor(exhibitKey).keys().list());
        logger.info("Deleted ExhibitApi with ID: " +  exhibitKey);
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
            path = "exhibitApi",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<ExhibitApi> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<ExhibitApi> query = ofy().load().type(ExhibitApi.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<ExhibitApi> queryIterator = query.iterator();
        List<ExhibitApi> exhibitApiList = new ArrayList<ExhibitApi>(limit);
        while (queryIterator.hasNext()) {
            exhibitApiList.add(queryIterator.next());
        }
        return CollectionResponse.<ExhibitApi>builder().setItems(exhibitApiList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Key<ExhibitApi> Id) throws NotFoundException {
        try {
            ofy().load().key(Id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find ExhibitApi with ID: " + Id);
        }
    }
}