package th.mfu.repository;

import org.springframework.data.repository.CrudRepository;
import th.mfu.domain.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Long> {

}
