require core-image-tstick.bb

IMAGE_FEATURES += "dev-pkgs doc-pkgs tools-sdk"

IMAGE_INSTALL += " \
    packagegroup-core-buildessential \
    packagegroup-tstick-devel \
"

symlink_pyth() {
    if [ ! -e ${IMAGE_ROOTFS}${bindir}/python ] && [ -e ${IMAGE_ROOTFS}${bindir}/python3 ]; then
        ln -s python3 ${IMAGE_ROOTFS}${bindir}/python
    fi
}
ROOTFS_POSTPROCESS_COMMAND += "symlink_pyth;"
