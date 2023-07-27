angular.module('templates-main', []).run(['$templateCache', function($templateCache) {
	$templateCache.put('app/ext/display-stats/html/add-statistics.html',
	"<meta name=\"after\" content=\".client-tile guac-client\">\n" +
	"\n" +
	"<!-- Guacamole display statistics -->\n" +
	"<guac-client-statistics client=\"client\"></guac-client-statistics>");
	$templateCache.put('app/ext/display-stats/templates/guacClientStatistics.html',
	"<dl class=\"client-statistics\">\n" +
	"\n" +
	"    <dt class=\"client-statistic desktop-fps\">\n" +
	"        {{ 'CLIENT.FIELD_HEADER_DESKTOP_FRAMERATE' | translate }}\n" +
	"    </dt>\n" +
	"    <dd ng-class=\"{ 'no-value' : !hasValue(statistics.desktopFps) }\">\n" +
	"        <span ng-show=\"hasValue(statistics.desktopFps)\"\n" +
	"            translate=\"CLIENT.INFO_FRAMERATE\"\n" +
	"            translate-values=\"{ VALUE : round(statistics.desktopFps) }\"></span>\n" +
	"    </dd>\n" +
	"\n" +
	"    <dt class=\"client-statistic server-fps\">\n" +
	"        {{ 'CLIENT.FIELD_HEADER_SERVER_FRAMERATE' | translate }}\n" +
	"    </dt>\n" +
	"    <dd ng-class=\"{ 'no-value' : !hasValue(statistics.serverFps) }\">\n" +
	"        <span ng-show=\"hasValue(statistics.serverFps)\"\n" +
	"            translate=\"CLIENT.INFO_FRAMERATE\"\n" +
	"            translate-values=\"{ VALUE : round(statistics.serverFps) }\"></span>\n" +
	"    </dd>\n" +
	"\n" +
	"    <dt class=\"client-statistic client-fps\">\n" +
	"        {{ 'CLIENT.FIELD_HEADER_CLIENT_FRAMERATE' | translate }}\n" +
	"    </dt>\n" +
	"    <dd ng-class=\"{ 'no-value' : !hasValue(statistics.clientFps) }\">\n" +
	"        <span ng-show=\"hasValue(statistics.clientFps)\"\n" +
	"            translate=\"CLIENT.INFO_FRAMERATE\"\n" +
	"            translate-values=\"{ VALUE : round(statistics.clientFps) }\"></span>\n" +
	"    </dd>\n" +
	"\n" +
	"    <dt class=\"client-statistic drop-rate\">\n" +
	"        {{ 'CLIENT.FIELD_HEADER_DROP_FRAMERATE' | translate }}\n" +
	"    </dt>\n" +
	"    <dd ng-class=\"{ 'no-value' : !hasValue(statistics.dropRate) }\">\n" +
	"        <span ng-show=\"hasValue(statistics.dropRate)\"\n" +
	"            translate=\"CLIENT.INFO_FRAMERATE\"\n" +
	"            translate-values=\"{ VALUE : round(statistics.dropRate) }\"></span>\n" +
	"    </dd>\n" +
	"\n" +
	"</dl>");
}]);
