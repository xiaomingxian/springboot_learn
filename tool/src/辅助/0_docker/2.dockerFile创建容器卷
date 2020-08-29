https://www.cnblogs.com/hydd/p/12660155.html

镜像的描述文件:


from centos #基于centos进行构建

1.dockerFile文件

    volume ['/volumeContainer1','/volumeContainer2']  #在centos /目录下新建2个 文件夹作为容器卷
    cmd echo 'make volume success '  #输出提示信息
    cmd /bin/bash

2.构建镜像
    docker build dockerFile文件

3.查看新生成的镜像信息 容器卷与宿主机的对应位置关系等
    docker inspect 镜像ID














#######################------------------说明---------------------#####################
#COMMAND为docker容器的默认启动命令，查看容器镜像的Dockerfile，一般体现为CMD["命令"]，如CMD["/bin/bash"] 就是这容器，默认启动了一个bash