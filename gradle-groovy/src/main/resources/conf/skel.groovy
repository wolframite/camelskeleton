route.routePackage = 'io.m18.skel.route'
route.name = [
	'DemoRoute'
]

route.Demo.from = "file:demo?autoCreate=true&delete=true"
route.Demo.to = "log:io.m18.skel.Demo?level=INFO"
route.Demo.wireTap = "log:io.m18.skel.WireTap?level=DEBUG"
