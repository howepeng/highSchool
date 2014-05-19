<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="title:'功能导航',border:false,fit:true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="系统菜单" data-options="iconCls:'icon-save',selected:true">
            <ul id='layout_west_tree'class="easyui-tree"
                data-options="
                    url:'${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',
                    parentField:'pid',
                    lines:true,
                    onLoadSuccess:function(node,data){$(this).tree('collapseAll')},
                    onClick:function(node){
                        if(node.attributes.url){
                            var url ='${pageContext.request.contextPath}/'+node.attributes.url;
                            addTabs({title:node.text,href:url,closable:true,tools : [ {
                        iconCls : 'icon-mini-refresh',
                        handler : function() {
                            refreshTab(node.text);
                        }
                    } ]});
                        }
                    }
                    "></ul>
        </div>
        <!--  <div title="Title2" data-options="iconCls:'icon-reload'">content2</div>-->
    </div>
</div>
