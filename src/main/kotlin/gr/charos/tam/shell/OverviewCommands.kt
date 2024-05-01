package gr.charos.tam.shell

import gr.charos.tam.domain.Report
import gr.charos.tam.domain.Resource
import gr.charos.tam.domain.ResourceExpiration
import gr.charos.tam.service.CalendarManager
import gr.charos.tam.service.ResourceManager
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@ShellComponent
class OverviewCommands(rm: ResourceManager, cm : CalendarManager) {


    val resourceManager =rm
    val calendarManager = cm


    @ShellMethod(key = ["list"])
    fun list() : List<ResourceExpiration> {
        return resourceManager.getResourcesExpirations();
    }

    @ShellMethod(key = ["report"])
    fun report(@ShellOption() from :String, @ShellOption to: String) : List<Report> {

        return resourceManager.getResourcesReport(
            LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )
    }

    @ShellMethod(key = ["calendar"])
    fun calendar()  {
        calendarManager.createCalendar();
    }


    @ShellMethod(key = ["add"])
    fun create( @ShellOption id : String,
                @ShellOption name : String,
                @ShellOption company : String,
                @ShellOption dateStarted : String,
                @ShellOption totalDays: Int) : Resource {
        val resource = Resource(
                            id,
                            name,
                            company,
                            LocalDate.parse(dateStarted, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            totalDays)

        resourceManager.saveResource(resource);
        return resource;
    }

    @ShellMethod(key = ["leave"])
    fun saveLeave(  @ShellOption id : String,
                    @ShellOption from : String,
                    @ShellOption to : String = ""
                   ) : Resource {
        val leaveFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        var leaveTo = leaveFrom;
        if (to.isNotEmpty()) {
            leaveTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
        var resource = resourceManager.getAllResources().first { resource -> resource.id == id }
        resource.addLeave(leaveFrom, leaveTo)
        resourceManager.saveResource(resource)

        return resource;
    }

    @ShellMethod(key = ["extension"])
    fun saveLeave(  @ShellOption id : String,
                    @ShellOption days : Int
    )  {
       resourceManager.addExtensionInDays(id,days)
    }

}

