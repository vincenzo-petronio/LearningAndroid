package it.localhost.app.mobile.learningandroid.repository;

import java.util.List;

import bolts.Task;
import it.localhost.app.mobile.learningandroid.data.model.EntityBase;
import it.localhost.app.mobile.learningandroid.repository.specification.Specification;

/**
 * @author vincenzo.petronio on 01/10/2018.
 */
public interface IRepository<T extends EntityBase> {

    Task<Boolean> addAsync(T entity);

    Task<Boolean> addAsync(Iterable<T> entities);

    Task<Boolean> updateAsync(T entity);

    Task<Boolean> removeAsync(T entity);

    Task<Boolean> removeAsync(Specification specification);

    Task<List<T>> queryAsync(Specification specification);
}
