#!/bin/bash
#该方式不起作用
#source $(pwd)/cicd/dev/.env
#echo "环境变量: $(pwd)/cicd/dev/.env"
#"$GITLAB_USER_NAME" "$CI_COMMIT_AUTHOR" "$PROJECT_NAME" "$CI_COMMIT_REF_NAME" "$CI_COMMIT_SHA" "$CI_COMMIT_MESSAGE" "$CHANGED_FILES" "true|false"
#作者
GITLAB_USER_NAME=$1
#邮箱
CI_COMMIT_AUTHOR=$2
#project name
PROJECT_NAME=$3
#branch or tag name
CI_COMMIT_REF_NAME=$4
#commit id
CI_COMMIT_SHA=$5
#commit message
CI_COMMIT_MESSAGE=$6
#变更文件
CHANGED_FILES=$7
#状态 成功或失败
STATE=$8

echo "sonar-url: ${SONARQUBE_HOST}/dashboard?branch=${CI_COMMIT_REF_NAME}&id=${SONARQUBE_KEY}"
#PROJECT_URL="http://192.168.23.19/shdy/shdy-task"
#CFL=$(echo "$CHANGED_FILES" | awk '{$1=$1;gsub(" ", "\n"); print}')
#CFL=$CHANGED_FILES
#CFL=""
#for file in "$@"
#do
#  CFL+="$file"
#done
CFL=$(echo "$CHANGED_FILES" | sed 's/ /\\n/g')

#颜色
COLOR=""
STATE_DESCR=""
if [ "$STATE" = "true" ]; then
  COLOR="green"
  STATE_DESCR="成功"
else
  COLOR="red"
  STATE_DESCR="失败"
  exit 1
fi
echo "发布状态: $STATE_DESCR"
echo "变更文件:
$CFL"

title='应用发布'
time="$(date "+%Y-%m-%d")"
times="$(date "+%H:%M:%S")"
xingqi="$(date "+%A")"
public_ip=$(curl -s https://api.ipify.org)
#runner 中需要apt-get install -y ifconfig
ip=$(ifconfig | grep inet | awk 'NR==3{print $2}')
lsblk=$(df -h / | awk '{print $4"/"$2 , $5}' | tail -n 1 )
mem_info=$(free -h) # 运行 free -h 命令并将结果保存到变量 mem_info 中
total_memory=$(echo "$mem_info" | grep "Mem:" | awk '{print $2}') # 从输出结果中提取第二列（Total）的值作为总内存大小
mem=$(free | grep Mem | awk '{print $3/$2 * 100.0}')
cpu=$(top -b -n1 | grep "Cpu(s)" | awk '{print $2}')

#组装URL
gitlab_commit_url="${MODULE_URL}/-/commit/${CI_COMMIT_SHA}"
sonarqube_branch_url="${SONARQUBE_HOST}/dashboard?branch=${CI_COMMIT_REF_NAME}&id=${SONARQUBE_KEY}"
#weixin
#curl --location 'https://open.feishu.cn/open-apis/bot/v2/hook/5d00f1b4-8bfb-4e07-b027-90a31c478464' \
#--header 'Content-Type: application/json' \
#--data '
#{
#  "msgtype": "markdown",
#  "markdown": {
#    "content": "开发环境\n
#      >发布时间: <font color=\"comment\">'"$time $times $xingqi"'</font>
#      >项目名: <font color=\"comment\">'"$PROJECT_NAME"'</font>
#      >分支名: <font color=\"comment\">'"$BRANCH_NAME"'</font>
#      >触发者: <font color=\"comment\">'"$AUTHOR_NAME"'</font>
#      >状态: <font color='"$COLOR"'>'"$STATE"'</font>
#      >HOST: <font color=\"comment\">'"$public_ip:$ip"'</font>
#      >DISK: <font color=\"comment\">'"$lsblk"'</font>
#      >MEM: <font color=\"comment\">'"$total_memory,$mem%"'</font>
#      >CPU: <font color=\"comment\">'"$cpu%"'</font>
#      >描述: <font color='"$COLOR"'>'"$COMMIT"'</font>
#      >变更文件:
#<font color=\"comment\">'"$changedFileList"'</font>"
#  }
#}'
#http://192.168.23.19/shdy/shdy-task/-/commit/6b9fa816dd1e5bfeae1aba0bdf4329a31d017fc1

#feishu
curl -X POST -H "Content-Type: application/json" \
        -d '{
          "msg_type":"post",
          "content": {
            "post": {
              "zh_cn": {
                "title": "构建'"$PROJECT_NAME"'",
                "content": [
                  [
                    {"tag": "text", "text": "'"时间：$time $times $xingqi\n"'"},
                    {"tag": "text", "color": "'"$COLOR"'", "text": "'"状态：$STATE_DESCR\n"'"},
                    {"tag": "text", "text": "'"作者：$GITLAB_USER_NAME: "'"},
                    {"tag": "a", "href": "'"$CI_COMMIT_AUTHOR"'", "text": "'"$CI_COMMIT_AUTHOR\n"'"},
                    {"tag": "text", "text": "'"项目：$PROJECT_NAME\n"'"},
                    {"tag": "text", "text": "'"分支："'"},
                    {"tag": "a", "href": "'"$MODULE_URL"'", "text": "'"$CI_COMMIT_REF_NAME\n"'"},
                    {"tag": "text", "text": "'"COMMIT_SHA："'"},
                    {"tag": "a", "href": "'"${gitlab_commit_url}"'", "text": "'"$CI_COMMIT_SHA\n"'"},
                    {"tag": "text", "text": "'"SONARQUBE："'"},
                    {"tag": "a", "href": "'"${sonarqube_branch_url}"'", "text": "http://192.168.111.103:9001\n"},
                    {"tag": "text", "text": "'"HOST:$public_ip:$ip\n"'"},
                    {"tag": "text", "text": "'"DISK:$lsblk\n"'"},
                    {"tag": "text", "text": "'"MEM:$total_memory,$mem%\n"'"},
                    {"tag": "text", "text": "'"CPU:$cpu%\n"'"},
                    {"tag": "text", "text": "'"描述：$CI_COMMIT_MESSAGE\n"'"},
                    {"tag": "text", "text": "'"清单：\n"'"},
                    {"tag": "text", "text": "'"$CFL"'"}
                  ]
                ]
              }
            }
          }
        }' "${WEBHOOK_FEI_SHU}"