#
# This is a bit of a rushed mess.
#
VERSION=0.0
jar_distfiles=README COPYING COPYING.LESSER
native_distfiles=$(jar_distfiles) native/README

native_jjmpeg=$(wildcard native/*/*jjmpeg.so native/*/*jjmpeg.dll)

dist_dir=jjmpeg-$(VERSION)

src_distfiles=$(jar_distfiles) \
	--exclude .svn \
	src \
	Makefile \
	webstart \
	native/README \
	native/Makefile \
	native/Makefile.libs \
	native/config.make \
	native/jni \
	native/jjmpeg \
	$(wildcard native/*/Makefile) \
	build.xml \
	--exclude private nbproject


bin_distfiles=$(jar_distfiles) \
	$(native_distfiles) \
	$(native_jjmpeg) \
	$(wildcard native/*/README*) \
	dist/jjmpeg.jar

.PHONY: webstart all native src

all: native

tar: src_tar bin_tar

clean:
	rm -rf classes
	rm -rf dist
	(cd native ; make clean )

src_tar: jar
	rm -rf $(dist_dir)
	mkdir $(dist_dir)
	tar cf - $(src_distfiles) | ( cd $(dist_dir) ; tar xf -)
	tar cjf $(dist_dir).tar.bz2 $(dist_dir)
	rm -rf $(dist_dir)

bin_tar: jar
	rm -rf $(dist_dir)
	mkdir $(dist_dir)
	tar cf - $(bin_distfiles) | ( cd $(dist_dir) ; tar xf -)
	tar cjf $(dist_dir)-bin.tar.bz2 $(dist_dir)
	rm -rf $(dist_dir)

jar: dist/jjmpeg.jar

dist/jjmpeg.jar: 
	cp src/au/notzed/jjmpeg/AVAbstract.java.original src/au/notzed/jjmpeg/AVAbstract.java
	make classes/classes
	mkdir -p dist
	jar cf dist/jjmpeg.jar $(jar_distfiles) -C classes au/notzed/jjmpeg

classes/classes:
	rm -rf classes
	mkdir classes
	javac -d classes -sourcepath src `find src -name '*.java'`
	touch classes/classes

native: dist/jjmpeg.jar
	( cd native ; make )
	cp native/mswin-amd64/AVAbstract.java src/au/notzed/jjmpeg/AVAbstract.java
	classes/classes
	cp src/au/notzed/jjmpeg/AVAbstract.java.original src/au/notzed/jjmpeg/AVAbstract.java
	mkdir -p dist
	jar cf dist/jjmpeg.jar $(jar_distfiles) -C classes au/notzed/jjmpeg
	cp native/mswin-amd64/jjmpeg.dll dist/jjmpeg.dll

webstart:
	( cd webstart ; make )
	( cd native ; make webstart )
