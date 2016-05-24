[#macro usergroupSelection userGroups formPath="" selectedUserGroupId=-1]

    <select class="user-admin__usergroup-selection" name="${ formPath }">
        <option valuu="">Kies...</option>
        [#list userGroups as userGroup]
            <option value="${ userGroup.id }" [#if userGroup.id == selectedUserGroupId]selected="selected"[/#if]>${ userGroup.name }</option>
        [/#list]
    </select>
[/#macro]