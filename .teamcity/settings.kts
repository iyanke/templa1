import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    vcsRoot(HttpsGithubComIyankeBigdata)
    vcsRoot(HttpsGithubComIyankeTempla)

    buildType(FromTemplate)

    template(Templ)
}

object FromTemplate : BuildType({
    templates(Templ)
    name = "from template"
})

object Templ : Template({
    name = "templ"

    vcs {
        root(HttpsGithubComIyankeBigdata)
    }

    features {
        vcsLabeling {
            id = "BUILD_EXT_1"
            enabled = false
            vcsRootId = "${HttpsGithubComIyankeBigdata.id}"
            branchFilter = ""
        }
    }
})

object HttpsGithubComIyankeBigdata : GitVcsRoot({
    name = "https://github.com/iyanke/bigdata"
    url = "https://github.com/iyanke/bigdata"
    branch = "refs/heads/master"
})

object HttpsGithubComIyankeTempla : GitVcsRoot({
    name = "https://github.com/iyanke/templa"
    url = "https://github.com/iyanke/templa"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "iyanke"
        password = "credentialsJSON:cfd37527-d9b2-4549-8552-222a74f6df96"
    }
})
