inherit tstick-image

IMAGE_FEATURES += "doc-pkgs"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-wild-core \
    packagegroup-wild-utils \
    packagegroup-wild-network-utils \
    \
    packagegroup-tstick \
    \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"
