route.routePackage = 'net.wolframite.camelskeleton.route'
route.name = [
	'DemoRoute'
]

route.Demo.from = "file://" + System.getProperty("user.dir") + "/demo?autoCreate=true&delete=true"
route.Demo.to = "log:net.wolframite.camelskeleton.Demo?level=INFO"
route.Demo.wireTap = "log:net.wolframite.camelskeleton.WireTap?level=DEBUG"

route.Demo.names.to = "Logger"
route.Demo.names.demoProcessor = "MyProcessor"
