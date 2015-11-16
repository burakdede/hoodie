import tornado.web
import tornado.ioloop
import tornado.httpserver
import os

from tornado.options import define,options


class BaseHandler(tornado.web.RequestHandler):
	pass


class RestHandler(BaseHandler):

	def get(self):
		self.write("get method called")

	def post(self):
		args = self.get_arguments()

		# print arguments
		for arg in args:
			logger.info(arg)

		self.write("post method called")

	def head(self):
		self.write("head method called")

	def delete(self):
		self.write("delete method called")


class Application(tornado.web.Application):

	def __init__(self):
		handlers = [
			(r"/rest", RestHandler)
		]

		settings = dict(
			app_title = "Sample Rest Api For Hoodie",
			template_path = os.path.join(os.path.dirname(__file__), "static"),
			static_path = os.path.join(os.path.dirname(__file__), "static"),
			cookie_secret = "11oETzKXQAGaYdkL5gEmGeJJFuYh7EQnp2XdTP1o/Vo=",
			xsrf_cookie = True,
			autoescape = True
		)

		tornado.web.Application.__init__(self, handlers, **settings)


def main():
		tornado.options.parse_command_line()
		http_server = tornado.httpserver.HTTPServer(Application())
		http_server.listen(8888)
		tornado.ioloop.IOLoop.instance().start()


if __name__ == "__main__":
	main()
