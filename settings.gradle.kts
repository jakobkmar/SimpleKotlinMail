val projectName = "simplekotlinmail"

rootProject.name = projectName

include("$projectName-core")

include("$projectName-server")
include("$projectName-client")
include("$projectName-html")

include("$projectName-test")

enableFeaturePreview("VERSION_CATALOGS")
