# play-slick-mysql
Playing with Scala, Play, Slick and MYSQL

The Play Slick module makes Slick a first-class citizen of Play. It consists of two features:

Integration of Slick into Play's application lifecycle.
Support for Play database evolutions.
Because Slick creates and manages both a connection pool and a thread pool, integrating Slick with Play boils down to ensure that all resources allocated by Slick are shutted down when a Play application is stopped (or reloaded).

The plugin has its own release cycle and therefore is not integrated into either core Play or Slick.
