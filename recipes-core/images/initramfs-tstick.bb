# initramfs for live images, based on core-image-minimal-initramfs
LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

KERNEL_MODULES = " \
    amdgpu \
    i915 \
    isofs \
    nouveau \
"

UTILS = " \
    util-linux-lsblk \
    usbutils \
    pciutils \
"

PACKAGE_INSTALL = " \
    initramfs-live-boot \
    initramfs-reboot \
    udev base-passwd \
    busybox \
    ${ROOTFS_BOOTSTRAP_INSTALL} \
    ${@' '.join(['kernel-module-'+m for m in d.getVar('KERNEL_MODULES').split()])} \
    ${UTILS} \
"

IMAGE_FEATURES = ""
#IMAGE_CLASSES_forcevariable = ""

export IMAGE_BASENAME = "initramfs-tstick"
IMAGE_LINGUAS = ""

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

BAD_RECOMMENDATIONS += "busybox-syslog"

tstick_init_postprocess() {
    # we don't need the kernel image embedded in the initramfs, that's silly
    rm -rvf ${IMAGE_ROOTFS}/boot

    # remove the automount rules/script from udev-extraconf
    rm -vf ${IMAGE_ROOTFS}${sysconfdir}/udev/rules.d/automount.rules
    rm -vf ${IMAGE_ROOTFS}${sysconfdir}/udev/scripts/mount.sh
}
ROOTFS_POSTPROCESS_COMMAND_append = "tstick_init_postprocess;"
